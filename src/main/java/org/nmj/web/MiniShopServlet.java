package org.nmj.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nmj.config.DatabaseConfig;
import org.nmj.dao.OrderHistoryDaoImpl;
import org.nmj.dao.ShoppingCartDaoImpl;
import org.nmj.dto.CategoryDto;
import org.nmj.dto.ProductDto;
import org.nmj.dto.ShoppingCartDto;
import org.nmj.model.*;
import org.nmj.service.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/")
public class MiniShopServlet extends HttpServlet {
    private CategoryService categoryService;
    private ProductService productService;
    private UserService userService;
    private ShoppingCartService shoppingCartService;
    private OrderHistoryService orderHistoryService;

    public void init() {
        categoryService = new CategoryServiceImplement();
        productService = new ProductServiceImpl();
        userService = new UserServiceImpl();
        shoppingCartService = new ShoppingCartServiceImpl(
                new ShoppingCartDaoImpl(DatabaseConfig.getDataSource())
                );
        orderHistoryService = new OrderHistoryServiceImpl(
                new OrderHistoryDaoImpl(DatabaseConfig.getDataSource())
        );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "/":
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    break;
                case "login":
                    showLoginFrom(request, response);
                    break;
                case "register":
                    showRegisterFrom(request, response);
                    break;
                case "dashboard":
                    showDashboard(request, response);
                    break;
                /*Category*/
                case "category-create":
                    request.getRequestDispatcher("category-create.jsp").forward(request, response);
                    break;
                case "category-list":
                    showCategoryList(request, response);
                    break;
                case "category-edit":
                    showCategoryEditForm(request, response);
                    break;
                /*Product*/
                case "product-create":
                    showProductCreateForm(request, response);
                    break;
                case "product-list":
                    showProductList(request, response);
                    break;
                case "product-edit":
                    showProductEditForm(request, response);
                    break;
                case "shopping-cart":
                    showShoppingCart(request, response);
                    break;
                case "order-history":
                    showOrderHistory(request, response);
                    break;
                case "order-history-for-admin":
                    showOrderHistoryAdminVersion(request, response);
                    break;
                default:
                    request.getRequestDispatcher("login").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private void showOrderHistoryAdminVersion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("orders", orderHistoryService.retrieveAllForAdmin());
        request.getRequestDispatcher("order-history-for-admin.jsp").forward(request, response);
    }

    private void showOrderHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("minishop-username")) {
                    request.setAttribute("username", cookie.getValue());
                }
            }
        }
        Long userId = retrieveUserIdByCookie(request, response);

        request.setAttribute("orders", orderHistoryService.retrieveAll(userId));

        request.getRequestDispatcher("order-history.jsp").forward(request, response);
    }

    private void showShoppingCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("minishop-username")) {
                    request.setAttribute("username", cookie.getValue());
                }
            }
            }
        Long userId = retrieveUserIdByCookie(request, response);

        request.setAttribute("shoppingCarts", shoppingCartService.retrieveAll(userId));

        request.getRequestDispatcher("shopping-cart.jsp").forward(request, response);

    }



    private void showRegisterFrom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    private void showLoginFrom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("minishop-username")) {
                    request.setAttribute("username", cookie.getValue());
                }

                if (cookie.getName().equals("minishop-userType")) {
                    request.setAttribute("userTypeName", cookie.getValue());
                }
            }
        }


        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

    private void showProductCreateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute("categories", categoryService.retrieveAll());
        for (CategoryDto category : categoryService.retrieveAll()) {
            System.out.println(category.getName());
        }
        request.getRequestDispatcher("product-create.jsp").forward(request, response);
    }

    private void showProductEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        request.setAttribute("product", productService.retrieveOne(id));
        request.getRequestDispatcher("product-edit.jsp").forward(request, response);
    }


    private void showProductList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("minishop-userType")) {
                    request.setAttribute("userTypeName", cookie.getValue());
                }
            }
        }
        System.out.println("hi: "+ productService.retrieveAll().size());
        request.setAttribute("products", productService.retrieveAll());
        request.getRequestDispatcher("product-list.jsp").forward(request, response);
    }

    private void showCategoryEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        request.setAttribute("category", categoryService.retrieveOne(id));
        request.getRequestDispatcher("category-edit.jsp").forward(request, response);
    }


    private void showCategoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("categories", categoryService.retrieveAll());
        request.getRequestDispatcher("category-list.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "login":
                    handleLogin(request, response);
                    break;
                case "register":
                    handleRegistration(request, response);
                    break;
                case "category-create":
                    handleCategoryCreate(request, response);
                    break;
                case "category-list":
                    System.out.println("Show List");
                    break;
                case "category-edit":
                    handleEdit(request, response);
                    break;
                case "product-create":
                    handleProductCreate(request, response);
                    break;
                case "category-delete":
                    handleCategoryDelete(request, response);
                    break;
                case "product-edit":
                    handleProductEdit(request, response);
                    break;
                case "product-delete":
                    handleProductDelete(request, response);
                    break;
                case "add-to-shopping-cart":
                    handleAddToCart(request, response);
                    break;
                case "check-out":
                    handleCheckOut(request, response);
                    break;
                default:
                    request.getRequestDispatcher("login").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private void handleCheckOut(HttpServletRequest request, HttpServletResponse response) throws Exception{
        final Long productId = Long.valueOf(request.getParameter("productId"));
        final Long id = Long.valueOf(request.getParameter("id"));
        final Integer quantity = Integer.valueOf(request.getParameter("quantity"));
        final Integer originalQuantity = Integer.valueOf(request.getParameter("originalQuantity"));
        final Long userId = retrieveUserIdByCookie(request, response);

        if(productId != null && id != null && quantity != null && originalQuantity != null) {
            if (quantity <= originalQuantity && quantity >= 0) {
                orderHistoryService.add(userId, productId, quantity);

                Integer leftQuantity = originalQuantity - quantity;
                shoppingCartService.updateQuantity(id, leftQuantity, userId, productId);
                response.sendRedirect("mini-shop?action=order-history");
            } else {
                request.setAttribute("error", "Invalid quantity.");
                request.setAttribute("shoppingCarts", shoppingCartService.retrieveAll(userId));
                response.sendRedirect("mini-shop?action=shopping-cart");
            }
        } else{
            request.setAttribute("error", "Something is wrong");
            request.setAttribute("shoppingCarts", shoppingCartService.retrieveAll(userId));
            response.sendRedirect("mini-shop?action=shopping-cart");
        }
    }

    private void handleAddToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final Long productId = Long.valueOf(request.getParameter("productId"));
        final Long userId = retrieveUserIdByCookie(request, response);
        final Integer quantity = Integer.valueOf(request.getParameter("quantity"));
        System.out.println("ProductId: " + productId);
        System.out.println("Quantity: "+ quantity);
        shoppingCartService.add(userId, productId, quantity);
        response.sendRedirect("mini-shop?action=product-list");
    }


    private void handleCategoryCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*Cookie[] cookies = request.getCookies();
        String username = null;
        if(cookies !=null){
            for(Cookie cookie: cookies){
                if (cookie.getName().equals("minishop-username")){
                    username = cookie.getValue();
                }
            }

            if(username==null){
                response.sendRedirect("mini-shop?action=login");
            }

            User user = userService.findByUsername(username)
                    .orElseThrow(()-> new Exception("User not found"));*/
        categoryService.create(
                request.getParameter("name"), retrieveUserIdByCookie(request, response)
        );
    }

    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int userType = Integer.parseInt(request.getParameter("userType"));
        System.out.println("Registering user: " + username);
        System.out.println("Password: " + password);
        Optional<User> x = userService.findByUsername(username);
        if (username.equals(x)) {
            request.setAttribute("error", "this username is already occupied");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            userService.create(username, password, userType);
            response.sendRedirect("mini-shop?action=login");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> userOptional = userService.findByUsername(username);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            final Cookie userCookie = new Cookie("minishop-username", username);
            userCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(userCookie);

            int userTypeId = userOptional.get().getUserType();
            UserType enumObject = UserType.fromId(userTypeId);
            if(enumObject!=null) {
                final Cookie userTypeCookie = new Cookie("minishop-userType", enumObject.getName());
                userTypeCookie.setMaxAge(60 * 60 * 24 * 365);
                response.addCookie(userTypeCookie);
            }
            response.sendRedirect("mini-shop?action=dashboard");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void handleProductCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        Long categoryId = Long.valueOf(request.getParameter("categoryId"));
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));

        productService.create(
                new ProductDto().initialize(
                        name,
                        description,
                        imageUrl,
                        price,
                        new CategoryDto(
                                categoryId, "", 0L
                        ),
                        retrieveUserIdByCookie(request, response)
                )
        );
        response.sendRedirect("mini-shop?action=product-list");
    }

    private void handleEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        CategoryDto ogCategory = categoryService.retrieveOne(id);

        if (ogCategory.getName().equals(name)) {
            request.setAttribute("error", "Cannot update since there's no difference in the data. Please click 'Cancel' to go back to the list.");
            request.setAttribute("category", categoryService.retrieveOne(id));
            request.getRequestDispatcher("category-edit.jsp").forward(request, response);

        } else {
            categoryService.edit(id, name, retrieveUserIdByCookie(request, response));
            response.sendRedirect("mini-shop?action=category-list");
        }
    }

    private void handleProductEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(Long.valueOf(request.getParameter("id")));
        productDto.setName(request.getParameter("name"));
        productDto.setPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter("price"))));
        productDto.setDescription(request.getParameter("description"));
        productDto.setImageUrl(request.getParameter("imageUrl"));
        productDto.setUpdatedBy(Long.valueOf(retrieveUserIdByCookie(request, response)));

        productService.edit(productDto);

        response.sendRedirect("mini-shop?action=product-list");
    }


    private void handleCategoryDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        categoryService.delete(id);
        response.sendRedirect("mini-shop?action=category-list");
    }

    private void handleProductDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        productService.delete(id);
        response.sendRedirect("mini-shop?action=product-list");
    }


    private Long retrieveUserIdByCookie(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie[] cookies = request.getCookies();
        String username = null;
//        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("minishop-username")) {
                    username = cookie.getValue();
                }
            }

            if (username == null) {
                response.sendRedirect("mini-shop?action=login");
            }

            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new Exception("User not found"));
            return user.getId();
        }
    }


