// 1. 사용자 정보를 관리하는 역할 (User 클래스)
class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getUserInfo() {
        return "Name: " + name + ", Email: " + email;
    }
}

// 2. 데이터베이스 저장 책임을 분리 (UserRepository 클래스)
class UserRepository {
    public void save(User user) {
        System.out.println("Saving user to database: " + user.getUserInfo());
        // 데이터베이스 저장 로직 (예: JDBC, JPA 등)
    }
}

// 3. 사용 예시
public class SRPMain {
    public static void main(String[] args) {
        User user = new User("Alice", "alice@example.com");
        UserRepository userRepository = new UserRepository();
        userRepository.save(user); // 저장 기능은 User가 아닌 UserRepository에서 수행
    }
}

