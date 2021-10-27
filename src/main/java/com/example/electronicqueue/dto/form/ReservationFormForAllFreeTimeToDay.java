package com.example.electronicqueue.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationFormForAllFreeTimeToDay {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
}
