const quickUnion = (x) => new Array(x).fill().map((_, i) => i);

const root = (ids, i) => {
  while (i !== ids[i]) {
    i = ids[i];
  }
  return i;
};

const connected = (ids, p, q) => root(ids, p) === root(ids, q);

const union = (ids, p, q) => {
  let i = root(ids, p);
  let j = root(ids, q);
  ids[i] = j;
  return ids;
};

export { quickUnion, connected, root, union };
