fun solution(products: List<String>, product: String) =
    print(
        products
            .mapIndexedNotNull { index, s -> if (s == product) index else null }
            .joinToString(" ")
    )
