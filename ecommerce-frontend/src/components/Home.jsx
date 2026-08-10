import Footer from "./Footer";
import "./Home.css";
import "./Footer.css";
import BannerSlider from "./BannerSlider";
import TopInfoBar from "./TopInfoBar";
import Header from "./Header";

export default function Home() {
  return (
    <>
      <TopInfoBar />
      <Header/>      
      <BannerSlider />
      <Footer />
    </>
  );
}