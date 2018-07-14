<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <tittle><h3>dostepne samochody</h3></tittle>
</head>
<body>
<table>
    <tr>
        <th>rok_produkcji</th>
        <th>marka</th>
        <th>model</th>
    </tr>
    <th th:each="car, index : ${cars1}">
        <td th:text="${car.yearProduction}"></td>
        <td th:text="${car.manufacturer}"></td>
        <td th:text="${car.model}"></td>

    </th>
</table>
</body>
</html>