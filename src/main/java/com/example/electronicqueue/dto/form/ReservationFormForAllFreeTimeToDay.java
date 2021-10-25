package com.example.electronicqueue.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationFormForAllFreeTimeToDay {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
}
