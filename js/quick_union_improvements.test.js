import {
  quickUnionImprovements,
  quickUnionLengths,
  union,
  connected
} from "./quick_union_improvements";

describe("quick union improvements", () => {
  it("tests union, connected", () => {
    let ids = quickUnionImprovements(10);
    let lengths = quickUnionLengths(10);
    let u = union(ids, lengths, 4, 3);
    expect(u.ids).toEqual([0, 1, 2, 4, 4, 5, 6, 7, 8, 9]);
    u = union(u.ids, u.lengths, 3, 8);
    expect(u.ids).toEqual([0, 1, 2, 4, 4, 5, 6, 7, 4, 9]);
    u = union(u.ids, u.lengths, 6, 5);
    expect(u.ids).toEqual([0, 1, 2, 4, 4, 6, 6, 7, 4, 9]);
    u = union(u.ids, u.lengths, 9, 4);
    expect(u.ids).toEqual([0, 1, 2, 4, 4, 6, 6, 7, 4, 4]);
    u = union(u.ids, u.lengths, 2, 1);
    expect(u.ids).toEqual([0, 2, 2, 4, 4, 6, 6, 7, 4, 4]);
    u = union(u.ids, u.lengths, 5, 0);
    expect(u.ids).toEqual([6, 2, 2, 4, 4, 6, 6, 7, 4, 4]);
    u = union(u.ids, u.lengths, 7, 2);
    expect(u.ids).toEqual([6, 2, 2, 4, 4, 6, 6, 2, 4, 4]);
    u = union(u.ids, u.lengths, 6, 1);
    expect(u.ids).toEqual([6, 2, 6, 4, 4, 6, 6, 2, 4, 4]);
    u = union(u.ids, u.lengths, 7, 3);
    expect(u.ids).toEqual([6, 2, 6, 4, 6, 6, 6, 2, 4, 4]);
  });
});
