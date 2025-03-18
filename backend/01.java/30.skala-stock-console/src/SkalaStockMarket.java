import java.util.Scanner;

public class SkalaStockMarket {
    private final PlayerRepository playerRepository = new PlayerRepository();
    private final StockRepository stockRepository = new StockRepository();
    private Player player = null;

    public void start() {

        //1. 주식 정보를 파일에서 읽어옴
        stockRepository.loadStockList();

        //2. 플레이어 정보를 파일에서 읽어옴
        playerRepository.loadPlayerList();

        //3. InputStream으로부터 입력되는 데이터를 받아서 파싱하는 Scanner 객체 생성
        Scanner scanner = new Scanner(System.in /* InputStream object return */);

        System.out.print("플레이어 ID를 입력하세요: ");
        String playerId = scanner.nextLine();
        player = playerRepository.findPlayer(playerId);
        if (player == null) { // 새로운 플레이어
            player = new Player(playerId);

            System.out.print("초기 투자금을 입력하세요: ");
            int money = scanner.nextInt();
            player.setPlayerMoney(money);
            playerRepository.addPlayer(player);
        }
        displayPlayerStocks();

        // 프로그램 루프
        boolean running = true;
        while (running) {
            System.out.println("\n=== 스칼라 주식 프로그램 메뉴 ===");
            System.out.println("1. 보유 주식 목록");
            System.out.println("2. 주식 구매");
            System.out.println("3. 주식 판매");
            System.out.println("4. 주식 상장");
            System.out.println("0. 프로그램 종료");

            System.out.print("선택: ");
            try {
                int code = scanner.nextInt();
                scanner.nextLine();

                switch (code) {
                    case 1:
                        displayPlayerStocks();
                        break;
                    case 2:
                        buyStock(scanner);
                        break;
                    case 3:
                        sellStock(scanner);
                        break;
                    case 4:
                        stockListing(scanner);
                        break;
                    case 0:
                        System.out.println("프로그램을 종료합니다...Bye");
                        running = false;
                        break;
                    default:
                        System.out.println("올바른 번호를 선택하세요.");
                }
            }
            catch (Exception e) {
                System.out.println("올바른 번호를 선택하세요.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    // 플레이어의 보유 주식 목록 표시
    private void displayPlayerStocks() {
        System.out.println("\n######### 플레이어 정보 #########");
        System.out.println("- 플레이어ID : " + player.getPlayerId());
        System.out.println("- 보유금액 : " + player.getPlayerMoney());
        System.out.println("- 보유 주식 목록");
        System.out.println(player.getPlayerStocksForMenu());
    }

    // 주식 목록 표시
    private void displayStockList() {
        System.out.println("\n=== 주식 목록 ===");
        System.out.println(stockRepository.getStockListForMenu());
    }

    // 주식 구매
    private void buyStock(Scanner scanner) {
        System.out.println("\n구매할 주식 번호를 선택하세요:");
        displayStockList();

        System.out.print("선택: ");
        int index = scanner.nextInt() - 1;

        Stock selectedStock = stockRepository.findStock(index);
        if (selectedStock != null) {
            System.out.print("구매할 수량을 입력하세요: ");
            int quantity = scanner.nextInt();

            int totalCost = Math.multiplyExact(selectedStock.getStockPrice(), quantity);
            int playerMoney = player.getPlayerMoney();
            if (totalCost <= playerMoney) {
                player.setPlayerMoney(playerMoney - totalCost);
                player.addStock(new PlayerStock(selectedStock, quantity));
                System.out.println(quantity + "주를 구매했습니다! 남은 금액: " + player.getPlayerMoney());

                // 변경된 내용을 파일로 저장
                playerRepository.savePlayerList();
            } else {
                System.out.println("ERROR: 금액이 부족합니다.");
            }
        } else {
            System.out.println("ERROR: 잘못된 선택입니다.");
        }
    }

    // 주식 판매
    private void sellStock(Scanner scanner) {
        System.out.println("\n판매할 주식 번호를 선택하세요:");
        displayPlayerStocks();

        System.out.print("선택: ");
        int index = scanner.nextInt() - 1;

        PlayerStock playerStock = player.findStock(index);
        if (playerStock != null) {
            System.out.print("판매할 수량을 입력하세요: ");
            int quantity = scanner.nextInt();

            // 어얼리 리턴
            if (quantity > playerStock.getStockQuantity()) {
                System.out.println("ERROR: 수량이 부족합니다.");
                return;
            }

            Stock baseStock = stockRepository.findStock(playerStock.getStockName());
            int playerMoney = player.getPlayerMoney() + baseStock.getStockPrice() * quantity;
            player.setPlayerMoney(playerMoney);

            playerStock.setStockQuantity(playerStock.getStockQuantity() - quantity);
            player.updatePlayerStock(playerStock);

            // 변경된 내용을 파일로 저장
            playerRepository.savePlayerList();

        } else {
            System.out.println("ERROR: 잘못된 선택입니다.");
        }
    }

    public void stockListing(Scanner scanner) {
        System.out.print("\n상장할 종목 이름을 입력해주세요: ");
        String stockName = scanner.nextLine();
        if (stockRepository.findStock(stockName) != null) {
            System.out.println("이미 존재하는 종목입니다.");
        } else {
            System.out.print("초기 금액을 입력하세요: ");
            int price = scanner.nextInt();
            stockRepository.addNewStock(stockName, price);
        }
    }

    public void stockDelisting(Scanner scanner) {
        System.out.println("\n상장폐지할 주식 번호를 선택하세요:");
        displayStockList();

        System.out.print("선택: ");
        int index = scanner.nextInt() - 1;

        Stock selectedStock = stockRepository.findStock(index);
        if (selectedStock != null) {
            stockRepository.removeStock(selectedStock);
        } else {
            System.out.println("ERROR: 잘못된 선택입니다.");
        }
    }
}
