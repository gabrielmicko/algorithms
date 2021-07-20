import { count, countFaster } from "./bruteforce_triples";

describe("bruteforce triples", () => {
  it("tests count", () => {
    expect(count([30, -40, -20, -10, 40, 0, 10, 5])).toBe(4);
    expect(countFaster([30, -40, -20, -10, 40, 0, 10, 5])).toBe(4);
  });
});
