import { Footer } from "flowbite-react";
import InputEmail from "./InputEmail";
import customFooterTheme from "./CustomFooterTheme";
import {
  BsDribbble,
  BsFacebook,
  BsGithub,
  BsInstagram,
  BsTwitter,
} from "react-icons/bs";

export default function Component() {
  return (
    <Footer container theme={customFooterTheme}>
      <div className="w-full">
        <div className="grid w-full justify-between sm:flex sm:justify-between md:flex md:grid-cols-1">
          <div className="flex flex-col">
            <Footer.Brand href="#" src="/logo.png" alt="STS Logo" name="STS"/>
            <div className="mt-auto">
              <Footer.Copyright
                by="Smart Training System"
                year={2024}
                className="text-gray-700"
              />
              <span className="text-sm text-gray-700">All rights reserved</span>
            </div>
          </div>
          <div className="grid grid-cols-2 gap-8 sm:mt-4 sm:grid-cols-3 sm:gap-6">
            <div>
              <Footer.Title title="company" className="text-gray-800" />
              <Footer.LinkGroup col>
                <Footer.Link className="text-gray-700" href="#">
                  About us
                </Footer.Link>
                <Footer.Link className="text-gray-700" href="#">
                  Blog
                </Footer.Link>
                <Footer.Link className="text-gray-700" href="#">
                  Contact us
                </Footer.Link>
                <Footer.Link className="text-gray-700" href="#">
                  Pricing
                </Footer.Link>
                <Footer.Link className="text-gray-700" href="#">
                  Testimonials
                </Footer.Link>
              </Footer.LinkGroup>
            </div>
            <div>
              <Footer.Title title="support" className="text-gray-800" />
              <Footer.LinkGroup col>
                <Footer.Link className="text-gray-700" href="#">
                  Help center
                </Footer.Link>
                <Footer.Link className="text-gray-700" href="#">
                  Terms of service
                </Footer.Link>
                <Footer.Link className="text-gray-700" href="#">
                  Legal
                </Footer.Link>
                <Footer.Link className="text-gray-700" href="#">
                  Privacy policy
                </Footer.Link>
                <Footer.Link className="text-gray-700" href="#">
                  Status
                </Footer.Link>
              </Footer.LinkGroup>
            </div>
            <div>
              <Footer.Title title="stay up to date" className="text-gray-800" />
              <Footer.LinkGroup col>
                <InputEmail></InputEmail>
              </Footer.LinkGroup>
            </div>
          </div>
        </div>
        <Footer.Divider />
        <div className="w-full sm:flex sm:items-center sm:justify-between">
          <div className="mt-4 flex space-x-6 sm:mt-0 sm:justify-center">
            <Footer.Icon href="#" icon={BsFacebook} className="text-gray-700" />
            <Footer.Icon
              href="#"
              icon={BsInstagram}
              className="text-gray-700"
            />
            <Footer.Icon href="#" icon={BsTwitter} className="text-gray-700" />
            <Footer.Icon href="#" icon={BsGithub} className="text-gray-700" />
            <Footer.Icon href="#" icon={BsDribbble} className="text-gray-700" />
          </div>
        </div>
      </div>
    </Footer>
  );
}
