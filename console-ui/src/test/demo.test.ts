import { add } from "./demo";
import Demo from "./Demo.vue";
import { mount } from "@vue/test-utils";

// demo1
test("first", () => {
  expect(1 + 1).toBe(2);
});

// function
test("second", () => {
  expect(add(1, 2)).toBe(3);
});

// component1
test("third", () => {
  expect(Demo).toBeTruthy();
});

// component2
test("fourth", () => {
  console.log(Demo);
});

// component3
test("fifth", () => {
  const wrapper = mount(Demo, {
    props: {
      name: "YJK",
    },
  });

  expect(wrapper.text()).toContain("YJK");
});
