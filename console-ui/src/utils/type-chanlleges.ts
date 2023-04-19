type myPick<T, K extends keyof T> = {
  [P in K]: T[P];
};

type myReadonly<T> = {
  readonly [P in keyof T]: T[P];
};

type Person = {
  name: string;
  age: number;
  sex: string;
};

type MyPerson = myPick<Person, "name">;

const reads = (person: MyPerson) => {
  console.log(person.name);
};

reads({
  name: "yjk",
});
