const quickUnionImprovements = (x) => new Array(x).fill().map((_, i) => i);

const quickUnionLengths = (x) => new Array(x).fill(0);

const root = (ids, i) => {
  while (i !== ids[i]) {
    //ids[i] = ids[ids[i]];
    i = ids[i];
  }
  return i;
};

const connected = (ids, p, q) => root(ids, p) === root(ids, q);

const union = (ids, lengths, p, q) => {
  let i = root(ids, p);
  let j = root(ids, q);

  if (i !== j) {
    if (lengths[i] < lengths[j]) {
      ids[i] = j;
      lengths[j] = lengths[j] + 1;
    } else {
      ids[j] = i;
      lengths[i] = lengths[i] + 1;
    }
  }
  return { ids, lengths };
};

export { quickUnionImprovements, quickUnionLengths, connected, union };
