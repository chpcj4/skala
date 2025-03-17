// 할인 정책 인터페이스
interface DiscountPolicy {
    double applyDiscount(double price);
}

// 고정 금액 할인
class FixedDiscount implements DiscountPolicy {
    @Override
    public double applyDiscount(double price) {
        return price - 1000;
    }
}

// 퍼센트 할인
class PercentageDiscount implements DiscountPolicy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.9;
    }
}

// 새로운 할인 정책 추가 (블랙프라이데이 30% 할인)
class BlackFridayDiscount implements DiscountPolicy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.7;
    }
}

// 할인 서비스 (기존 코드 수정 없이 새로운 할인 정책 적용 가능)
class DiscountService {
    public double calculateDiscount(DiscountPolicy policy, double price) {
        return policy.applyDiscount(price);
    }
}

// 사용 예시
public class OCPMain {
    public static void main(String[] args) {
        DiscountService discountService = new DiscountService();

        DiscountPolicy fixedDiscount = new FixedDiscount();
        DiscountPolicy percentageDiscount = new PercentageDiscount();
        DiscountPolicy blackFridayDiscount = new BlackFridayDiscount();

        System.out.println("Fixed Discount Price: " + discountService.calculateDiscount(fixedDiscount, 5000));
        System.out.println("Percentage Discount Price: " + discountService.calculateDiscount(percentageDiscount, 5000));
        System.out.println("Black Friday Discount Price: " + discountService.calculateDiscount(blackFridayDiscount, 5000));
    }
}
