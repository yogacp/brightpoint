package app.brightpoint.com.adapters

import app.beslider.com.adapters.SliderAdapter
import app.beslider.com.utils.viewholder.ImageSliderViewHolder

class BesliderAdapter (val mListImages : ArrayList<String?>) : SliderAdapter() {

    override fun getItemCount(): Int {
        return mListImages.size
    }

    override fun onBindImageSlide(position: Int, imageSliderViewHolder: ImageSliderViewHolder) {
        imageSliderViewHolder.bindImageSlide(mListImages[position]!!)
    }

}