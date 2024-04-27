# EJB3_Stateful_ShoppingCart
## Demo Session Bean trong EJB 3 – Cài đặt ứng dụng Shopping Cart sử dụng Stateful
 * Công nghệ sử dụng:
   - Apache NetBeans IDE 20
   - Java: 18.0.2.1; Java HotSpot(TM) 64-Bit Server VM 18.0.2.1+1-1
   - GlassFish Server: 4.1 
* Mô tả chức năng
   - Giao diện đầu tiên của ứng dụng cho phép người dùng chọn các loại sách thông qua combo box chứa thông tin mô tả các loại sách (các bạn có thể áp dụng lưới để trình bày dữ liệu)
   - Người dùng chọn một mặt hàng bất kỳ và nhấn nút Add To Cart để đưa hàng vào giỏ hàng của họ
   - Thông tin chọn lựa của người dùng được chuyển đến Controller, Controller sẽ xác định Middleware Object trên Application Server (ở đây sẽ áp dụng Enterprise Java Bean – Session Bean) cần xử lý và cập nhật Session Bean instance
   - Trong quá trình cập nhật Session Bean, hệ thống có nhiệm vụ kiểm tra xem mặt hàng đã có trong giỏ hàng hay chưa. Nếu có thì tăng số lượng của mặt hàng đó lên, ngược lại đưa hàng vào giỏ với số lượng mặc định đầu tiên luôn là 1
   - Sau khi cập nhật xong, thông tin cập nhật thành công chuyển lại controller để controller quay lại trang người dùng chọn lựa để họ có thể chọn tiếp (thành công). Nếu không thành công sẽ có báo lỗi tương ứng
   - Khi user muốn xem giỏ hàng của mình, user click vào nút View Cart, thông tin một lần nữa chuyển đến Controller, Controller xác định Session Bean tương ứng, gọi phương thức lấy dữ liệu. Đồng thời tại lúc chọn Session Bean, Controller cũng xác định View tương ứng để    chuẩn bị đón nhận kết quả từ Session Bean để trình bày dữ liệu
   - Khi dữ liệu từ Session Bean được chuyển đến View, hệ thống sẽ render dữ liệu vào View. Khi thành công, Controller sẽ đưa View về phía người dùng hiển thị kết quả
   - Trong trang view, người dùng có thể Add thêm hàng bằng cách click vào link Add more. Nội dung yêu cầu người dùng chuyển vào Controller, Controller sẽ đưa trang chọn hàng và trình bày về phía người dùng để người dùng có thể dùng chức năng thêm hàng vào giỏ như đã       nêu ở trên
   - Ngoài ra trong trang view, người dùng có thể chọn một số mặt hàng để loại bỏ bớt ra khỏi giỏ hàng bằng cách check vào các check box tương ứng của mặt hàng, sau đó, user click nút Remove. Thông tin được đưa về Controller, Controller chọn Session Bean tương ứng, cập    nhật Model và lấy thông tin trở về, đồng thời xác định View tương ứng và thực hiện trình bày kết quả thông qua Controller để đưa cho người dùng thông qua chức năng View Cart
   - Trong trang checkout, khi có sản phẩm trong giỏ hàng thì mới thanh toán. Ngược lại, nếu không có sản phầm nào trong giỏ hàng thì chuyển đến trang giao diện đầu tiên.

* Mô hình tổng quát chức năng: <br>
   ![image](https://github.com/idiotman-2212/EJB3_Stateful_ShoppingCart/assets/82036270/cef38791-d279-4843-b445-ccc49ab2d1aa)

- Trang giao diện
![image](https://github.com/idiotman-2212/EJB3_Stateful_ShoppingCart/assets/82036270/f3459e84-f518-476b-b0d3-a60fd6f19464)

- Ảnh chọn sản phẩm
![image](https://github.com/idiotman-2212/EJB3_Stateful_ShoppingCart/assets/82036270/ed42fd97-972a-48fe-a190-584ad8069fee)

- Trang view cart
![image](https://github.com/idiotman-2212/EJB3_Stateful_ShoppingCart/assets/82036270/1b210ab9-342e-4d67-914f-7d71f6961f7d)

- Trang checkout
![image](https://github.com/idiotman-2212/EJB3_Stateful_ShoppingCart/assets/82036270/7871bd31-8564-47ed-9133-9c9dc01c25be)

#Design Pattern dùng trong dự án này.
## Factory method pattern
-Trong class **CartSesionBeanFactory**, phương thức **createCartSessionBean()** được sử dụng để tạo mới một đối tượng **CartSesionBean** và trả về nó. Điều này giúp tách rời quá trình tạo đối tượng từ client và cung cấp một cách linh hoạt để tạo ra các đối tượng phụ thuộc vào điều kiện hoặc yêu cầu cụ thể.
<pre>
   package chd.com;
/**
 *
 * @author DELL
 */
public class CartSesionBeanFactory {
     public static CartSesionBeanLocal createCartSessionBean() {
        // Tạo và trả về một đối tượng CartSesionBean
        return new CartSesionBean();
    }
}
</pre>


##Singelton method pattern
- Trong class **CartSesionBean**, chúng ta thấy một biến **static instance** được khai báo, cùng với một phương thức **getInstance()** để truy cập đến **instance** duy nhất của class này.
- Constructor của **CartSesionBean** được đặt là **private**, nghĩa là không thể tạo mới đối tượng **CartSesionBean** từ bên ngoài class. Điều này đảm bảo rằng chỉ có một đối tượng **CartSesionBean** được tạo ra, và nó chỉ có thể được truy cập thông qua phương thức **getInstance()**.
