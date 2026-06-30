import { Swiper, SwiperSlide } from "swiper/react";
import "./BannerSlider.css";
import "swiper/css";
import { Autoplay } from "swiper/modules";
import { useEffect, useState } from "react";


export default function BannerSlider() {
const [banners, setBanners] = useState ([]);


  useEffect (()=>
  {
      fetch("http://localhost:8088/api/banner/banners")
      .then((res)=> {
        if (!res.ok) {
        throw new Error(`HTTP Error: ${res.status}`);
      }
      return res.json();
      })
      .then((data)=> {
        console.log(data);
        setBanners(data);
      })
      .catch((err)=>console.log (err));
  },[]);
  
  return (
    <Swiper
    className="banner-slider"
     modules={[Autoplay]}
    slidesPerView={1}
    spaceBetween={20}
    loop={true}
    autoplay={
      {
        delay: 2000,
    disableOnInteraction: false,
    pauseOnMouseEnter: false,
      }
    }
    speed={800}
  >
      {banners.map((banner) => 
      (
        <SwiperSlide key={banner.id}>
          <img src={banner.imageUrl} alt={banner.title} 
          style={{width:"100%"}}/>
        </SwiperSlide>
      )
      )}
    </Swiper>
  );
}