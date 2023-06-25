package Model;


public class Inventory implements Cloneable {

	private static int i = 42;
	private  int id;
	private String product;
	private int quantity;

	public Inventory(String _product){ 
		this.id = i++;
		this.product = _product;
		this.quantity = 1;
	}
	public Inventory(String _product , int quantity){ 
		this.id = i++;
		this.product = _product;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
    public Inventory clone() {
        try {
            Inventory cloned = (Inventory) super.clone();
            cloned.product = new String(this.product);
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

	@Override
	public String toString() {

		String tableFormat = "%-10s %-30s %-10s\n";
		StringBuilder sb = new StringBuilder();

		// Table header
		//sb.append(String.format(tableFormat, "ID", "Product", "Quantity"));
		//sb.append(String.format(tableFormat, "----------", "------------------------------", "----------"));

		// Table rows
		sb.append(String.format(tableFormat, id, product,quantity));

		return sb.toString();
	}


}
