const isObject = (val: any): boolean => val && typeof val === "object";
const mergeArrayWithDedupe = (a: string[], b: string[]): string[] =>
  Array.from(new Set([...a, ...b]));

/**
 * Recursively merge the content of the new object to the existing one
 * Usage scenarios: State management, merging config objects;Config objects are usually nested, so we need to merge them recursively
 * @param {Object} target the existing object
 * @param {Object} obj the new object
 */
function deepMerge(target: Object, obj: Object): Object {
  const keys = Object.keys(obj);

  for (const key of keys) {
    const oldVal = target[key];
    const newVal = obj[key];

    if (isObject(oldVal) && isObject(newVal)) {
      target[key] = deepMerge(oldVal, newVal);
    } else if (Array.isArray(oldVal) && Array.isArray(newVal)) {
      target[key] = mergeArrayWithDedupe(oldVal, newVal);
    } else if (typeof oldVal === "undefined") {
      target[key] = newVal;
    }
  }

  return target;
}
export default deepMerge;
