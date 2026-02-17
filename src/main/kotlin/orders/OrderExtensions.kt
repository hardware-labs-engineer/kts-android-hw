package orders

/**
 * Applies a percentage discount to every product in the order.
 *
 * Hint: use [products] to read the current list, [removeProductById] and
 * [addProduct] to replace each product with a discounted copy.
 * Use [Product.copy] to create a new product with a modified price.
 *
 * @param discountPercent discount percentage (e.g. 10 means 10%)
 * @param logger optional callback invoked with a log message for each product
 */
fun Order.applyDiscount(
    discountPercent: Int,
    logger: ((String) -> Unit)? = null
) {
    products.forEach {
        val discountedPrice = it.price * (100 - discountPercent) / 100
        val discountedProduct = it.copy(price = discountedPrice)
        this.removeProductById(it.id)
        this.addProduct(discountedProduct)
        logger?.invoke("Applied discount to ${it.name}: $discountedPrice")
    }
}
