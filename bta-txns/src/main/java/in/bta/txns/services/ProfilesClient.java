package in.bta.txns.services;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="bta-profiles")    //(name="profiles", url = "http://localhost:9100")
@LoadBalancerClient(name="bta-profiles")
public interface ProfilesClient {
	
	@GetMapping("/{ahId}/exists")
	public Boolean checkAccountHolderExists(@PathVariable("ahId") Long ahId);

}
