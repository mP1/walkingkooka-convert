[![Build Status](https://github.com/mP1/walkingkooka-convert/actions/workflows/build.yaml/badge.svg)](https://github.com/mP1/walkingkooka-convert/actions/workflows/build.yaml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/mP1/walkingkooka-convert/badge.svg?branch=master)](https://coveralls.io/github/mP1/walkingkooka-convert?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/walkingkooka-convert.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-convert/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/walkingkooka-convert.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-convert/alerts/)
[![J2CL compatible](https://img.shields.io/badge/J2CL-compatible-brightgreen.svg)](https://github.com/mP1/j2cl-central)



Converters may be used to convert values of one type to another. This is particularly useful when executing a weakly typed language, such as an XPath selector which uses expressions within predicates.



# Converters

The [Converters](https://github.com/mP1/walkingkooka-convert/blob/master/src/main/java/walkingkooka/convert/Converters.java)
class contains many static factory methods. The methods are camel case named in the style of source to destination type.
Number means any of the JRE Number sub classes (Byte, Short, Integer, Long, Float, Double, BigInteger, BigDecimal).

- bigDecimalToBoolean
- booleanToNumber
- characterOrStringThen converts characters to a String if necessary and then passes that String to a wrapped Converter.
- collection Tries many Converters until success.
- customToString Wraps a Converter providing a custom #toString
- fake Useful for testing.
- localDateLocalDateTime
- localDateNumber
- localDateString
- localDateTimeLocalDate
- localDateTimeLocalTime
- localDateTimeNumber
- localDateTimeString
- localTimeLocalDateTime
- localTimeNumber
- localTimeString
- mapper
- never
- numberToLocalDate
- numberToLocalDateTime
- numberToLocalTime
- numberToNumber
- numberString
- object // converts anything when target type is Object
- objectString Simply calls Object#toString
- parser This accepts Strings and calls a Parser.
- simple Returns the value if it is the same Class as the target. Note due to J2cl limitations Class.isInstance is not possible.
- stringCharacter
- stringLocalDate
- stringLocalDateTime
- stringLocalTime
- stringNumber
- toBoolean Performs a test and uses that result to pick one of two values.
- toStringOrCharacter converts any value to String if necessary and then that to Character.
- truthyNumberBoolean Follows javascript truthy rules, zero means false, all other values are true.


