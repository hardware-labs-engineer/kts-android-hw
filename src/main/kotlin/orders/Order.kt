package orders

class Order(
    val id: Int
) : PriceCalculator {

    private val _products: MutableList<Product> = mutableListOf()

    /** Read-only view of products in the order. */
    val products: List<Product> get() = _products.toList()

    var status: OrderStatus = OrderStatus.Created
        private set

    /**
     * Adds a product to the order.
     * If the product is null, it should be ignored.
     */
    fun addProduct(product: Product?) {
        product?.let { _products.add(it) }
    }

    /**
     * Removes the first product matching [productId].
     */
    fun removeProductById(productId: Int) {
        val index = _products.indexOfFirst { it.id == productId }
        _products.removeAt(index)
    }

    /**
     * Returns the total price of all products in the order.
     */
    override fun calculateTotal(): Int {
        var sum = 0
        _products.forEach { sum += it.price }
        return sum
    }

    /**
     * Marks the order as paid.
     * Throws [IllegalStateException] if the order has no products.
     */
    fun pay() {
        if (_products.isEmpty()) {
            throw IllegalStateException("Order has no products")
        } else { status = OrderStatus.Paid }
    }

    /**
     * Cancels the order with the given reason.
     * If [reason] is null, use "Unknown reason".
     */
    fun cancel(reason: String?) {
        status = if (reason == null) {
            OrderStatus.Cancelled("Unknown reason")
        } else {
            OrderStatus.Cancelled(reason)
        }
    }
}
