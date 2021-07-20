import { quickUnion, union, connected } from "./quick_union";

describe("quick union", () => {
  it("tests union, connected", () => {
    let ids = quickUnion(10);
    ids = union(ids, 4, 3);
    expect(ids).toEqual([0, 1, 2, 3, 3, 5, 6, 7, 8, 9]);
    ids = union(ids, 3, 8);
    expect(ids).toEqual([0, 1, 2, 8, 3, 5, 6, 7, 8, 9]);
    ids = union(ids, 6, 5);
    expect(ids).toEqual([0, 1, 2, 8, 3, 5, 5, 7, 8, 9]);
    ids = union(ids, 9, 4);
    expect(ids).toEqual([0, 1, 2, 8, 3, 5, 5, 7, 8, 8]);
    ids = union(ids, 2, 1);
    expect(ids).toEqual([0, 1, 1, 8, 3, 5, 5, 7, 8, 8]);
    expect(connected(ids, 8, 9)).toBe(true);
    expect(connected(ids, 5, 4)).toBe(false);
    ids = union(ids, 5, 0);
    expect(ids).toEqual([0, 1, 1, 8, 3, 0, 5, 7, 8, 8]);
    ids = union(ids, 7, 2);
    expect(ids).toEqual([0, 1, 1, 8, 3, 0, 5, 1, 8, 8]);
    ids = union(ids, 6, 1);
    expect(ids).toEqual([1, 1, 1, 8, 3, 0, 5, 1, 8, 8]);
    ids = union(ids, 7, 3);
    expect(ids).toEqual([1, 8, 1, 8, 3, 0, 5, 1, 8, 8]);
  });
});
