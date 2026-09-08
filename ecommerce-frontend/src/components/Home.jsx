import BannerSlider from "./BannerSlider";
import Footer from "./Footer";
import "./Footer.css";
import Header from "./Header";
import TopHeader from "./TopHeader";

export default function Home() {
  return (
    <>
    <TopHeader/>
      <Header/>      
      <BannerSlider />
      <Footer />
    </>
  );
}