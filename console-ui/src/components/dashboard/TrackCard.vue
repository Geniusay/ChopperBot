<template>
  <!-- loading spinner -->
  <div
    v-if="loading"
    class="h-full d-flex flex-grow-1 align-center justify-center"
  >
    <v-progress-circular indeterminate color="primary"></v-progress-circular>
  </div>
  <div v-else>
    <h6 class="text-h6 pa-5 d-flex align-center">
      <span class="flex-1">Table</span>
    </h6>
    <v-table class="pa-3">
      <thead>
        <tr>
          <th class="text-left" v-for="header in headers" :key="header.text">
            {{ header.text }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in items" :key="item.id">
          <td>#{{ item.id }}</td>
          <td>
            <div class="d-flex align-center py-1">
              <v-avatar size="40" class="elevation-1 grey lighten-3">
                <img :src="item.user.avatar" />
              </v-avatar>
              <div class="ml-1">
                <div class="font-weight-bold">{{ item.user.name }}</div>
                <div class="caption">
                  {{ item.user.email }}
                </div>
              </div>
            </div>
          </td>
          <td>{{ item.date }}</td>
          <td>{{ item.company }}</td>
          <td>{{ item.amount }}</td>
          <td>
            <div v-if="item.status === 'PENDING'" class="text-warning">
              <v-icon size="small" color="warning">mdi-circle-medium</v-icon>
              <span>Pending</span>
            </div>
            <div v-if="item.status === 'PAID'" class="text-success">
              <v-icon size="small" color="success">mdi-circle-medium</v-icon>
              <span>Paid</span>
            </div>
          </td>
          <td>
            <v-btn
              size="small"
              variant="text"
              icon="mdi-open-in-new"
              @click="open(item)"
            >
            </v-btn>
          </td>
        </tr>
      </tbody>
    </v-table>
  </div>
</template>

<script setup lang="ts">
const loading = ref(true);

const headers = [
  { text: "Order Id", align: "start", value: "id" },
  {
    text: "User",
    sortable: false,
    value: "user",
  },
  { text: "Date", value: "date" },
  { text: "Company", value: "company" },
  { text: "Amount", value: "amount" },
  { text: "Status", value: "status" },
  { text: "", sortable: false, align: "right", value: "action" },
];
const items = [
  {
    id: "2837",
    user: {
      name: "John Simon",
      email: "johnsimon@blobhill.com",
      avatar: "https://i.pravatar.cc/150?img=1",
    },
    date: "2020-05-10",
    company: "BlobHill",
    amount: 52877,
    status: "PAID",
  },
  {
    id: "2838",
    user: {
      name: "Greg Cool J",
      email: "cool@caprimooner.com",
      avatar: "https://i.pravatar.cc/150?img=2",
    },
    date: "2020-05-11",
    company: "Caprimooner",
    amount: 2123,
    status: "PENDING",
  },
  {
    id: "2839",
    user: {
      name: "Samantha Bush",
      email: "bush@catloveisstilllove.com",
      avatar: "https://i.pravatar.cc/150?img=3",
    },
    date: "2020-05-11",
    company: "CatLovers",
    amount: 12313,
    status: "PENDING",
  },
  {
    id: "2840",
    user: {
      name: "Ben Howard",
      email: "ben@indiecoolers.com",
      avatar: "https://i.pravatar.cc/150?img=4",
    },
    date: "2020-05-12",
    company: "IndieCoolers",
    amount: 9873,
    status: "PAID",
  },
  {
    id: "2841",
    user: {
      name: "Jack Candy",
      email: "jack@candylooove.com",
      avatar: "https://i.pravatar.cc/150?img=5",
    },
    date: "2020-05-13",
    company: "CandyLooove",
    amount: 29573,
    status: "PAID",
  },
];

const open = (item) => {};

onMounted(() => {
  setTimeout(() => {
    loading.value = false;
  }, 1000);
});
</script>

<style lang="scss" scoped>
.v-table {
  table {
    padding: 4px;
    padding-bottom: 8px;

    th {
      text-transform: uppercase;
      white-space: nowrap;
    }

    td {
      border-bottom: 0 !important;
    }

    tbody {
      tr {
        transition: box-shadow 0.2s, transform 0.2s;

        &:not(.v-data-table__selected):hover {
          box-shadow: 0 3px 15px -2px rgba(0, 0, 0, 0.12);
          transform: translateY(-4px);
        }
      }
    }
  }
}
</style>
