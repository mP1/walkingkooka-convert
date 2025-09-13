[![Build Status](https://github.com/mP1/walkingkooka-convert/actions/workflows/build.yaml/badge.svg)](https://github.com/mP1/walkingkooka-convert/actions/workflows/build.yaml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/mP1/walkingkooka-convert/badge.svg?branch=master)](https://coveralls.io/github/mP1/walkingkooka-convert?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/walkingkooka-convert.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-convert/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/walkingkooka-convert.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-convert/alerts/)
![](https://tokei.rs/b1/github/mP1/walkingkooka-convert)
[![J2CL compatible](https://img.shields.io/badge/J2CL-compatible-brightgreen.svg)](https://github.com/mP1/j2cl-central)



Converters may be used to convert values of one type to another. This is particularly useful when executing a weakly typed language, such as an XPath selector which uses expressions within predicates.



# Converters

The [Converters](https://github.com/mP1/walkingkooka-convert/blob/master/src/main/java/walkingkooka/convert/Converters.java) class contains many static factory methods to fetch a {@link Converter} which converts a source
to a requested target type. Different Converters only support a small limited source and target types and can be combined 
into sequence which are tried one by one [ConverterCollect](https://github.com/mP1/walkingkooka-convert/blob/master/src/main/java/walkingkooka/convert/ConverterCollection.java).

*Number* in the names below means any of the JRE `java.lang.Number` sub-classes (Byte, Short, Integer, Long, Float, Double, BigInteger, BigDecimal).

- booleanToNumber
- characterOrStringToString converts characters to a String if necessary and then passes that String to a wrapped Converter.
- chain A `Converter` which converts a value to some intermediate type and then converts success values to another `Converter` using the original target type.
- collection Tries each of the given `Converter` until success.
- customToString Wraps a Converter providing a custom #toString
- fake Useful for testing, such as situations where a Converter should never be called.
- hasTextToString
- localDateToLocalDateTime
- localDateToNumber
- localDateToString
- localDateTimeToLocalDate
- localDateTimeToLocalTime
- localDateTimeToNumber
- localDateTimeToString
- localTimeToLocalDateTime
- localTimeToNumber
- localTimeToString
- mapper
- never
- numberToBoolean Follows javascript truthy rules, zero means false, all other values are true.
- numberToLocalDate
- numberToLocalDateTime
- numberToLocalTime
- numberToNumber
- numberToString
- object // converts anything when target type is Object
- objectToString Simply calls Object#toString
- parser This accepts Strings and calls a Parser.
- simple Returns the value if it is the same Class as the target. Note due to J2cl limitations Class.isInstance is not possible.
- stringToStringOrCharacter converts any value to String if necessary and then that to Character.
- stringToLocalDate
- stringToLocalDateList
- stringToLocalDateTime
- stringToLocalDateTimeList
- stringToLocalTime
- stringToLocalTimeList
- stringToNumber
- stringToBooleanList
- toBoolean Performs a test and uses that result to pick one of two values.

Note more `Converter` implementations are available in many different repos to support useful conversions.