package kz.iitu.itse1903.abimoldayeva.model.dto;

import kz.iitu.itse1903.abimoldayeva.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveDTO {
    private String userName;
    private Long ticketId;

}
