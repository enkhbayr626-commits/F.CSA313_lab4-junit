package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой")
    void ninetyIsExactlyA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String result = calc.letterGrade(90);

        // Assert
        assertEquals("A", result);
    }

    @Test
    @DisplayName("100 оноо A дүн байх ёстой")
    void oneHundredIsA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String result = calc.letterGrade(100);

        // Assert
        assertEquals("A", result);
    }

    @Test
    @DisplayName("0 оноо F дүн байх ёстой")
    void zeroIsF() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String result = calc.letterGrade(0);

        // Assert
        assertEquals("F", result);
    }

    @Test
    @DisplayName("-1 оноо оруулахад exception шидэх ёстой")
    void negativeScoreThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(-1)
        );
    }

    @Test
    @DisplayName("101 оноо оруулахад exception шидэх ёстой")
    void scoreOverHundredThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(101)
        );
    }

    @Test
    @DisplayName("Бүх оноо дээд хэмжээтэй үед нийлбэр 100 байх ёстой")
    void totalScoreIsHundred() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        double result = calc.totalScore(10, 40, 10, 10, 30);

        // Assert
        assertEquals(100.0, result);
    }

    @Test
    @DisplayName("Ирц сөрөг үед exception шидэх ёстой")
    void negativeAttendanceThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(-5, 40, 10, 10, 30)
        );
    }

    @Test
    @DisplayName("Лабын оноо 40-өөс их үед exception шидэх ёстой")
    void labOverMaximumThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(10, 41, 10, 10, 30)
        );
    }

    @ParameterizedTest
    @DisplayName("Үсгэн дүнгийн хязгааруудыг шалгах")
    @CsvSource({
            "95, A",
            "90, A",
            "89.99, B",
            "80, B",
            "70, C",
            "60, D",
            "59.99, F",
            "0, F"
    })
    void letterGradeBoundaries(double score, String expected) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String result = calc.letterGrade(score);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("Олон төрлийн нийлбэр оноог шалгах")
    @CsvSource({
            "10, 40, 10, 10, 30, 100",
            "0, 0, 0, 0, 0, 0",
            "5, 20, 5, 5, 15, 50",
            "8, 30, 7, 9, 25, 79"
    })
    void totalScoreCases(
            double att,
            double lab,
            double quiz1,
            double quiz2,
            double exam,
            double expected) {

        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        double result = calc.totalScore(att, lab, quiz1, quiz2, exam);

        // Assert
        assertEquals(expected, result);
    }
}
