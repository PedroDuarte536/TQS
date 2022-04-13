### f) Has your project passed the defined quality gate? Elaborate your answer
It did pass with 1 bug, 0 vulnerabilities, 1 security hotspot, 1h28m debt, 33 code smeels, 77.9% code coverage and 0% code duplication.

### g) Explore the analysis results and complete with a few sample issues, as applicable.
| Issue | Problem description | How to solve |
| Bug | Save and re-use this "Random". | Random should not be instantiated inside of a function. |
| Code Smells | Rename this field "NUMBERS_COUNT" to match the regular expression '^[a-z][a-zA-Z0-9]*$'. | Format constant's names. |
| Code Smells | Refactor the code in order to not assign to this loop counter from within the loop body. | Place the counter inside of the for header. |
| Code Smells | Reorder the modifiers to comply with the Java Language Specification. | "public static" instead of "static public". |