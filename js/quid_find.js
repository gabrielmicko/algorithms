const quickFind = (x) => new Array(x).fill().map((_, i) => i);

const connected = (ids, p, q) => ids[p] === ids[q];

const union = (ids, p, q) => {
  const pid = ids[p];
  const qid = ids[q];

  return ids.map((i) => {
    if (i === pid) {
      return qid;
    }
    return i;
  });
};

export { quickFind, connected, union };
