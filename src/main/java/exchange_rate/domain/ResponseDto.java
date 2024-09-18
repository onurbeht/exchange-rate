package exchange_rate.domain;

public record ResponseDto(
        String result,
        String base_code,
        String target_code,
        float conversion_result) {

}
