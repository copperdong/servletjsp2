<%@page import="java.time.*"%>
<!DOCTYPE html>
<html>
    <head>
        <title>EL 3.0 Demo</title>
        <style>
            table {
                border: 1px solid #ababab;
            }
            td {
                border: 2px solid #ababab;
            }
            th {
                border: 3px solid #ababab;
            }
            th, td {
                padding: 2px 15px 2px 15px;
            }
        </style>
    </head>
    <body>
        <p>
        <h3>Creating Set</h3>
        Set of integers: ${{1, 2, 3, 4, 5}}
        </p>
        <p>
        <h3>Creating List</h3>
        List of flowers: ${["Aster", "Carnation", "Rose"]}
        </p>
        <p>
        <h3>Creating Map</h3>
        ${{ "Canada":"Ottawa", "China":"Beijing", "France":"Paris" }}
        </p>
        <h3>Access Map Entry</h3>
        ${{ "Canada":"Ottawa", "China":"Beijing", "France":"Paris" }["Canada"]}
        </p>
        <p>
        <h3>Static method:</h3>
        <span>
            &radic;<span style="text-decoration:overline;">&nbsp;36&nbsp;</span>
            = ${Math.sqrt(36)}
        </span>
        </p>
        <p>
        <h3>Static field:</h3>
        <span>
            &pi; = ${Math.PI}
        </span>
        </p>
        <p>Map
            ${[1,2,3,4].stream().map(n -> Math.sqrt(n)).toList()}
        </p>
        Today is ${LocalDate.now()}
        <p>
            Original: ${cities}
        </p>
        <p>
            List[i]: ${cities[1]}
        </p>
        <p>
            Limit: ${cities.stream().limit(3).toList()}
        </p>
        <p>
            Sorted: ${cities.stream().sorted().toList()}
        </p>
        <p>
            Average: ${[1, 3, 5, 7].stream().average().get()}
        </p>
        <p>
            Sum: ${[1, 3, 5, 7].stream().sum()}
        </p>
        <p>
            Count: ${cities.stream().count()}
        </p>
        <p>
            Min: ${[1,3,100,1000].stream().min().get()}
        </p>
        <p>
            Max: ${[1,3,100,1000].stream().max().get()}
        </p>
        <p>
            Map: ${[1, 3, 5].stream().map(x -> 2 * x).toList()}
        </p>
        <p>
            Map: ${cities.stream().map(x -> x.toUpperCase()).toList()}
        </p>
        <p>
            Filter: ${cities.stream().filter(x -> x.startsWith("S")).toList()}
        </p>
        <p>
            forEach: ${cities.stream().forEach(x -> pageContext.out.println(x))}
        </p>
        <p>
        <ul>
            <!--
            ${cities.stream().map(x -> "--><li>"+=x+="</li><!--").toList()}
            -->
        </ul>
        </p>
        <%--
            <c:forEach items="${cities.stream().map(x->x.toUpperCase()).toArray()}" 
                       var="city">
                <br/>${city}
            </c:forEach>
            --%>
        </p>
        <p>
        <table>
            <tr><th>Street</th><th>City</th></tr>
            <!--${addresses.stream().map(a->"--><tr><td>"+=a.streetName+="</td><td>"+=a.city+="</td></tr><!--").toList()}-->
        </table>
        </p>
        <p>
        <table>
            <tr><th>x</th><th>x<sup>2</sup></th></tr>
            <!--${[1,3,5,7].stream().map(a->"--><tr><td>"+=a+="</td><td>"
                  +=a*a+="</td></tr><!--").toList()}-->
        </table>
        </p>
        <p>
           
            String join: ${"<ol><li>" += String.join("</li><li>", cities) += "</li></ol>"}
        </p>
        <p>
            ${empty cities? "" : "<ol><li>" 
              += String.join("</li><li>", cities.stream().sorted().toList()) 
              += "</li></ol>"}
        </p>
        <p>
            <!-- formatting rules: https://docs.oracle.com/javase/tutorial/java/data/numberformat.html-->
        <h3>Number formatting:</h3>
            ${String.format("%-10.2f%n", 125.178)}
        </p>
        <p>
        <h3>Date formatting:</h3>
            ${d = LocalDate.now().plusDays(2);
              String.format("%tB %te, %tY%n", d, d, d)}
        </p>
    </body>
</html>
