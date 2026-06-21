import sliderImage1 from "../assets/slider-image-1.png";
import sliderImage2 from "../assets/slider-image-2.png";
import sliderImage3 from "../assets/slider-image-3.png";
import { Swiper, SwiperSlide } from "swiper/react";
import "./BannerSlider.css";
import "swiper/css";
import { Autoplay } from "swiper/modules";

export default function BannerSlider() {
  return (
    <Swiper
    className="banner-slider"
     modules={[Autoplay]}
    slidesPerView={1}
    spaceBetween={20}
    autoplay={{
        delay:  2000,
        disableOnInteraction: false,
  }}
  loop={true}
    >
      <SwiperSlide>
        <img
          src={sliderImage1}
          alt="Banner 1"
          style={{ width: "100%" }}
        />
      </SwiperSlide>
      <SwiperSlide>
        <img
          src={sliderImage2}
          alt="Banner 1"
          style={{ width: "100%" }}
        />
      </SwiperSlide>
      <SwiperSlide>
        <img
          src={sliderImage3}
          alt="Banner 1"
          style={{ width: "100%" }}
        />
      </SwiperSlide>
    </Swiper>
  );
}