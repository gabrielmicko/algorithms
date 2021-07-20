import { binarySearch } from "./boolean_search";

describe("boolean search", () => {
  it("tests count", () => {
    expect(binarySearch([100, 200, 300, 400, 500, 600, 700, 800], 800)).toBe(7);
    expect(binarySearch([100, 200, 300, 400, 500, 600, 700, 800], 400)).toBe(3);
    expect(binarySearch([100, 200, 300, 400, 500, 600, 700, 800], 500)).toBe(4);
    expect(binarySearch([100, 200, 300, 400, 500, 600, 700, 800], 900)).toBe(
      -1
    );
  });
});
