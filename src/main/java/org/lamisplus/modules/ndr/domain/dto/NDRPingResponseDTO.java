package org.lamisplus.modules.ndr.domain.dto;

import lombok.Data;

@Data
public class NDRPingResponseDTO {
    String serverDate;
    String serverTime;
    String serverTimeZone;
}
