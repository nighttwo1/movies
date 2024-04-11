package com.nighttwo1.presentation.ui.tvseries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.SeasonDetail
import com.nighttwo1.domain.model.SeasonId
import com.nighttwo1.domain.model.TvDetail
import com.nighttwo1.domain.model.TvId
import com.nighttwo1.domain.usecase.GetSeasonDetailUseCase
import com.nighttwo1.domain.usecase.GetTvSeriesDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val getTvSeriesDetailUseCase: GetTvSeriesDetailUseCase,
    private val getSeasonDetailUseCase: GetSeasonDetailUseCase
) : ViewModel() {
    val tvDetailResult = MutableStateFlow<NetworkResult<TvDetail>>(NetworkResult.Ready())

    fun getTvDetail(id: String) = viewModelScope.launch {
        getTvSeriesDetailUseCase(id).collect(tvDetailResult)
    }

    val seasonDetailResult = MutableStateFlow<NetworkResult<SeasonDetail>>(NetworkResult.Ready())
    fun getSeasonDetail(seriesId: TvId, seasonNumber: Int) = viewModelScope.launch {
        getSeasonDetailUseCase(seriesId = seriesId.value.toString(), seasonNumber = seasonNumber.toString()).collect(
            seasonDetailResult
        )
    }
}