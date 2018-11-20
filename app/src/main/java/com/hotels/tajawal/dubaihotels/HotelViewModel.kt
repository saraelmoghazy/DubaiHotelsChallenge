package com.hotels.tajawal.dubaihotels

import android.arch.lifecycle.MutableLiveData
import com.hotels.tajawal.dubaihotels.base.BaseFetchObserver
import com.hotels.tajawal.dubaihotels.base.BaseViewModel
import com.hotels.tajawal.dubaihotels.base.UseCase
import com.hotels.tajawal.dubaihotels.hotels.GetHotelsUseCase
import com.hotels.tajawal.dubaihotels.hotels.model.Hotel
import com.hotels.tajawal.dubaihotels.hotels.model.HotelResponse

/**
 * Created by Sara Elmoghazy.
 */
class HotelViewModel : BaseViewModel() {

    @UseCase(R.id.getHotels)
    private val getHotelsUseCase = GetHotelsUseCase()
    private val hotelsLiveData: MutableLiveData<List<Hotel>> = MutableLiveData()
    private val selectedHotelLiveData: MutableLiveData<Hotel> = MutableLiveData()
    private val showFullImageLiveData: MutableLiveData<String> = MutableLiveData()
    private val hotelObserver = object :
            BaseFetchObserver<HotelResponse>(this, R.id.getHotels) {
        override fun onNext(m: HotelResponse) {
            super.onNext(m)
            setIsLoading(false)
            hotelsLiveData.postValue(m.hotel)
        }
    }

    init {
        start()
        setIsLoading(true)
        getHotelsUseCase.execute(hotelObserver)
    }

    fun getHotels() = hotelsLiveData

    fun getSelectedHotelLiveData() = selectedHotelLiveData

    fun onHotelSelected(hotel: Hotel) {
        selectedHotelLiveData.postValue(hotel)
    }

    fun getShowFullImageLiveData() = showFullImageLiveData

    fun onHotelImageClicked(url: String) {
        showFullImageLiveData.postValue(url)
    }
}