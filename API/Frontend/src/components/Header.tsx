import { useCallback } from "react";
import { Link } from "react-router-dom"
import { Button } from "../components/ui/button";
import { LuMapPin, LuPlane, LuUser } from "react-icons/lu";
//import { useAuthStore } from "@store/authStore";
import { AiOutlineLoading3Quarters } from "react-icons/ai";

function Header() {
	/*
	const auth = useAuthStore();

	const buildProfileButton = useCallback(() => {
		if (auth.isLogging || auth.isUserLoading) {
			return (
				<Button variant="ghost" className="p-6">
					<AiOutlineLoading3Quarters className="animate-spin text-2xl mr-2" />
				</Button>
			);
		}

		if (!auth.user) {
			return (
				<Link to="/login">
					<Button variant="ghost" className="p-6">
						<LuUser className="text-2xl mr-2" />
						<p className="text-xl">Login</p>
					</Button>
				</Link>
			);
		}

		return (
			<Link to="/admin">
				<Button variant="ghost" className="p-6">
					<LuUser className="text-2xl mr-2" />
					<p className="text-xl">Profile</p>
				</Button>
			</Link>
		);
	}, [auth]);
	*/

	return (
		<header className="py-8 px-12 flex items-center">
			<div className="flex items-center mx-auto gap-12">
				<Link to="/">
					<Button variant="ghost" className="p-6">
						<LuMapPin className="text-2xl mr-2" />
						<p className="text-xl">Map</p>
					</Button>
				</Link>
				<Link to="/trips">
					<Button variant="ghost" className="p-6">
						<LuPlane className="text-2xl mr-2" />
						<p className="text-xl">Trips</p>
					</Button>
				</Link>
			</div>
			<div className="w-48 flex justify-end items-center">
				{/*buildProfileButton()*/}
			</div>
		</header>
	);
}

export default Header;
