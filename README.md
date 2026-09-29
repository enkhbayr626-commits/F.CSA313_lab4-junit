# Lab 04 — JUnit 5

## Оюутны мэдээлэл

- Нэр: Энхбаяр
- Оюутны код: B232270153

## Ашигласан орчин

- OS: Ubuntu on WSL2
- Java: OpenJDK 21.0.12.1
- Maven: Apache Maven 3.8.7
- JUnit Jupiter: 5.10.2
- Maven Surefire Plugin: 3.2.5

## Лабораторийн зорилго

JUnit 5 ашиглан Java програмд нэгжийн тест бичиж, ердийн утга, хязгаарын утга болон буруу оролтуудыг шалгах. Мөн parameterized болон mutation test ашиглан тестүүд кодын алдааг илрүүлж чадаж байгаа эсэхийг турших.

## GradeCalculator

`GradeCalculator` класс дараах хоёр үндсэн method-той:

- `letterGrade(double score)` — 0–100 оноог A, B, C, D, F үсгэн дүнд хөрвүүлнэ.
- `totalScore(double att, double lab, double quiz1, double quiz2, double exam)` — бүрэлдэхүүн оноонуудыг нийлүүлж нийт оноог тооцно.

Оролтын утга зөвшөөрөгдсөн хязгаараас гарвал `IllegalArgumentException` шиднэ.

## Нэгжийн тестүүд

Нийт **10 test method** бичсэн:

- Энгийн `@Test`: 8
- `@ParameterizedTest`: 2

Parameterized test-ийн тохиолдлууд тус тусдаа тоологдсон тул Maven нийт **20 тест** ажиллуулсан.

Шалгасан гол утгууд:

```text
90     -> A
89.99  -> B
60     -> D
59.99  -> F
0      -> F
100    -> A
-1     -> IllegalArgumentException
101    -> IllegalArgumentException
att=-5 -> IllegalArgumentException
lab=41 -> IllegalArgumentException
```

## Parameterized Test

### letterGradeBoundaries()

```text
95     -> A
90     -> A
89.99  -> B
80     -> B
70     -> C
60     -> D
59.99  -> F
0      -> F
```

### totalScoreCases()

```text
10, 40, 10, 10, 30 -> 100
0,  0,  0,  0,  0  -> 0
5, 20, 5, 5, 15     -> 50
8, 30, 7, 9, 25     -> 79
```

## Тест ажиллуулах

Тестүүдийг дараах командаар ажиллуулсан:

```bash
mvn test 2>&1 | tee results/mvn-test.txt
```

Maven `GradeCalculatorTest` классыг ажиллуулсан:

```text
Running mn.edu.must.sqat.GradeCalculatorTest
Tests run: 20, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Ингэснээр бүх 20 тест алдаагүй, амжилттай ажилласан.

## Mutation Test

Mutation test хийхдээ дараах нөхцөлийг:

```java
score >= 90
```

түр хугацаанд:

```java
score > 90
```

болгон өөрчилсөн.

Дараа нь:

```bash
mvn test 2>&1 | tee results/mvn-test-mutant.txt
```

командыг ажиллуулахад:

```text
Tests run: 20
Failures: 2
Errors: 0
Skipped: 0
BUILD FAILURE
```

гэсэн үр дүн гарсан.

`ninetyIsExactlyA()` тест болон `letterGradeBoundaries()`-ийн `90 -> A` тохиолдол унасан. Энэ нь хязгаарын тестүүд логикийн өөрчлөлтийг зөв илрүүлж байгааг харуулсан.

Mutation test-ийн дараа нөхцөлийг `score >= 90` болгон буцааж зассан. Тестүүдийг дахин ажиллуулахад:

```text
Tests run: 20
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

гэсэн эцсийн үр дүн гарсан.

## Дүгнэлт

Энэ лабораториор JUnit 5 ашиглан нэгжийн тест бичиж, `assertThrows`, `@ParameterizedTest` болон `@CsvSource` ашиглаж сурсан. Maven нийт 20 тест ажиллуулж, бүх тест амжилттай өнгөрсөн. Mutation test хийхэд 2 тест унасан нь `90` гэсэн хязгаарын утгыг шалгасан тестүүд логикийн алдааг зөв илрүүлж байгааг баталсан.
