Feature: Matrix Transformations

  Scenario: Multiplying by a translation matrix
    Given T ← translation(5, -3, 2)
    And "p" <- point(-3.0, 4.0, 5.0)
    Then T * p = point(2.0, 1.0, 7.0)

  Scenario: Multiplying by the inverse of a translation matrix
    Given T ← translation(5, -3, 2)
    And I ← inverse(T)
    And "p" <- point(-3.0, 4.0, 5.0)
    Then I * p = point(-8.0, 7.0, 3.0)

  Scenario: Translation does not affect vectors
    Given T ← translation(5, -3, 2)
    And "v" <- vector(-3.0, 4.0, 5.0)
    Then T * v = v

  Scenario: A scaling matrix applied to a point
    Given T ← scaling(2, 3, 4)
    And "p" <- point(-4.0, 6.0, 8.0)
    Then T * p = point(-8.0, 18.0, 32.0)

  Scenario: A scaling matrix applied to a vector
    Given T ← scaling(2, 3, 4)
    And "v" <- vector(-4.0, 6.0, 8.0)
    Then T * v = vector(-8.0, 18.0, 32.0)