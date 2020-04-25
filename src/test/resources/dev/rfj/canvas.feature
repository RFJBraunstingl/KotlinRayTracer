Feature: Canvas

  Scenario: Creating a canvas
    Given c ← canvas(10, 20)
    Then c.width = 10
    And c.height = 20
    And every pixel of c is color(0.0, 0.0, 0.0)

  Scenario: Writing pixels to a canvas
    Given c ← canvas(10, 20)
    And "red" <- color(1.0, 0.0, 0.0)
    When c.write_pixel(2, 3, red)
    Then c.pixel_at(2, 3) = red

  Scenario: Constructing the PPM header
    Given c ← canvas(5, 3)
    When ppm ← canvas_to_ppm(c)
    Then lines 1-3 of ppm are
    """
    P3
    5 3
    255
    """