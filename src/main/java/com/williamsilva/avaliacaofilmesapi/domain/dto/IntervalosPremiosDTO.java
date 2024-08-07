package com.williamsilva.avaliacaofilmesapi.domain.dto;

import java.util.Collections;
import java.util.List;

public class IntervalosPremiosDTO {

    private List<ProdutorPremioDTO> min;
    private List<ProdutorPremioDTO> max;

    public List<ProdutorPremioDTO> getMin() {
        return Collections.unmodifiableList(min);
    }

    public void setMin(List<ProdutorPremioDTO> min) {
        this.min = min;
    }

    public List<ProdutorPremioDTO> getMax() {
        return Collections.unmodifiableList(max);
    }

    public void setMax(List<ProdutorPremioDTO> max) {
        this.max = max;
    }
}
