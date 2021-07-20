import { quickFind, union, connected } from "./quick_find";

describe("quick find", () => {
  it("tests connected and union", () => {
    let ids = quickFind(4);
    ids = union(ids, 0, 1);
    ids = union(ids, 2, 3);
    expect(connected(ids, 0, 1)).toBe(true);
    expect(connected(ids, 2, 3)).toBe(true);
    expect(connected(ids, 0, 2)).toBe(false);
    expect(connected(ids, 1, 2)).toBe(false);
    expect(ids).toEqual([1, 1, 3, 3]);
    ids = union(ids, 1, 2);
    expect(ids).toEqual([3, 3, 3, 3]);
    expect(connected(ids, 0, 3)).toBe(true);
  });
});
