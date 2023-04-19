export type Email = {
  id: number;
  read: boolean;
  starred: boolean;
  time: string;
  subject: string;
  title: string;
  content: string;
  labels: string[];
};

export const inboxList: Email[] = [
  {
    id: 1,
    read: false,
    starred: true,
    time: "15 min",
    subject: "Brunch this weekend?",
    title: "Ali Connors",
    content:
      "I'll be in your neighborhood doing errands this weekend. Do you want to hang out?",
    labels: ["work"],
  },
  {
    id: 2,
    read: false,
    starred: false,
    time: "2 hr",
    subject: "Summer BBQ",
    title: "me, Scrott, Jennifer",
    content: "Wish I could come, but I'm out of town this weekend.",
    labels: [],
  },
  {
    id: 3,
    read: false,
    starred: false,
    time: "6 hr",
    subject: "Oui oui",
    title: "Sandra Adams",
    content: "Do you have Paris recommendations? Have you ever been?",
    labels: ["work", "invoice"],
  },
  {
    id: 4,
    read: true,
    starred: false,
    time: "12 hr",
    subject: "Birthday gift",
    title: "Trevor Hansen",
    content: "Have any ideas about what we should get Heidi for her birthday?",
    labels: [],
  },
  {
    id: 5,
    read: true,
    starred: true,
    time: "18hr",
    subject: "Recipe to try",
    title: "Britta Holt",
    content: "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
    labels: ["invoice"],
  },
];

export const starredList: Email[] = [
  {
    id: 1,
    read: false,
    starred: true,
    time: "15 min",
    subject: "Brunch this weekend?",
    title: "Ali Connors",
    content:
      "I'll be in your neighborhood doing errands this weekend. Do you want to hang out?",
    labels: ["work"],
  },
];
