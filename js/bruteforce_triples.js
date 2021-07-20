//Count how many triples sum up to 0
import { binarySearch } from "./boolean_search";
const count = (n) => {
  const length = n.length;
  let c = 0;

  for (let i = 0; i < length; i++) {
    for (let j = i + 1; j < length; j++) {
      for (let k = j + 1; k < length; k++) {
        if (n[i] + n[j] + n[k] === 0) {
          c++;
        }
      }
    }
  }
  return c;
};

//Count how many triples sum up to 0

const countFaster = (n) => {
  const length = n.length;
  let c = 0;
  let ns = n.sort();
  for (let i = 0; i < length; i++) {
    for (let j = i + 1; j < length; j++) {
      const searchFor = -(ns[i] + ns[j]);
      const findNum = binarySearch(ns.slice(j + 1), searchFor);
      if (findNum != -1) {
        c++;
      }
    }
  }
  return c;
};

export { count, countFaster };
