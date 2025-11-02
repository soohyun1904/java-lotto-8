package lotto.view;

import lotto.dto.LottoDto;
import lotto.dto.LottoResultDto;
import lotto.dto.LottosDto;

public class OutputView {
    private final static String LOTTO_PURCHASE_MESSAGE = "%s개를 구매했습니다.";

    public void printLottos(LottosDto lottos){
        System.out.println(String.format(LOTTO_PURCHASE_MESSAGE, lottos.count()));
        lottos.lottos().forEach(this::printLotto);
        System.out.println();
    }

    private void printLotto(LottoDto lotto){
        System.out.println(lotto.numbers());
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public void printResult(LottoResultDto result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        // 5등부터 1등까지 출력
        printRankStatistics("FIFTH", "3개 일치 (5,000원)", result);
        printRankStatistics("FOURTH", "4개 일치 (50,000원)", result);
        printRankStatistics("THIRD", "5개 일치 (1,500,000원)", result);
        printRankStatistics("SECOND", "5개 일치, 보너스 볼 일치 (30,000,000원)", result);
        printRankStatistics("FIRST", "6개 일치 (2,000,000,000원)", result);

        // 수익률 출력
        System.out.printf("총 수익률은 %.1f%%입니다.%n", result.rateOfReturn());
    }

    private void printRankStatistics(String rank, String description, LottoResultDto result) {
        long count = result.getCountByRank(rank);
        System.out.printf("%s - %d개%n", description, count);
    }
}
