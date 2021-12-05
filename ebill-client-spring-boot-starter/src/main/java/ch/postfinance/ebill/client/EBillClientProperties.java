package ch.postfinance.ebill.client;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EBillClientProperties {

	/**
	 * The URI of the eBill web service is required, example:
	 * https://ebill.postfinance.ch/B2BService/B2BService.svc
	 */
	@NotBlank
	private String uri;

}
