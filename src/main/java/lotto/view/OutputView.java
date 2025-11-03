package lotto.view;

import lotto.dto.LottoDto;
import lotto.dto.LottoResultDto;
import lotto.dto.LottosDto;

import java.util.List;

public class OutputView {
    private final static String LOTTO_PURCHASE_MESSAGE = "%s개를 구매했습니다.";

    private static final String STATISTICS_HEADER = "당첨 통계";
    private static final String STATISTICS_SEPARATOR = "---";
    private static final String RATE_OF_RETURN_MESSAGE = "총 수익률은 %.1f%%입니다.";
    private static final String RANK_FORMAT = "%s - %d개";

    private record RankInfo(String name, String description) {}

    private static final List<RankInfo> RANK_INFOS = List.of(
            new RankInfo("FIFTH", "3개 일치 (5,000원)"),
            new RankInfo("FOURTH", "4개 일치 (50,000원)"),
            new RankInfo("THIRD", "5개 일치 (1,500,000원)"),
            new RankInfo("SECOND", "5개 일치, 보너스 볼 일치 (30,000,000원)"),
            new RankInfo("FIRST", "6개 일치 (2,000,000,000원)")
    );

    public void printLottos(LottosDto lottos){
        System.out.printf(String.format(LOTTO_PURCHASE_MESSAGE, lottos.count()));
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
        System.out.println(STATISTICS_HEADER);
        System.out.println(STATISTICS_SEPARATOR);

        RANK_INFOS.forEach(rank ->
                printRankStatistics(rank.name(), rank.description(), result)
        );

        System.out.printf(String.format(RATE_OF_RETURN_MESSAGE, result.rateOfReturn()));
    }

    private void printRankStatistics(String rank, String description, LottoResultDto result) {
        long count = result.getCountByRank(rank);
        System.out.printf(String.format(RANK_FORMAT, description, count));
    }
}
