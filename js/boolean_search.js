const binarySearch = (ids, key) => {
  let lo = 0;
  let hi = ids.length - 1;

  while (lo <= hi) {
    let mid = Math.round(lo + (hi - lo) / 2);
    if (key < ids[mid]) {
      hi = mid - 1;
    } else if (key > ids[mid]) {
      lo = mid + 1;
    } else {
      return mid;
    }
  }

  return -1;
};

export { binarySearch };
