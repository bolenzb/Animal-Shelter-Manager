import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import UserView from '../views/UserView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import ApplyView from '../views/ApplyView.vue';
import MainView from '../views/MainView.vue';
import ApplicationsList from '../components/ApplicationsList.vue';
import AdminView from '../views/AdminView.vue';
import AddPetView from '../views/AddPetView.vue';
import ForgotPasswordForm from '../components/ForgotPasswordForm.vue';
import AdoptionFormView from '../views/AdoptionFormView.vue';
import AdoptionApplicationsList from '../components/AdoptionApplicationsList.vue';
import PetView from '../views/PetView.vue';
import SearchView from '../views/SearchView.vue';
import AboutUsView from '../views/AboutUsView.vue';
import DonateView from '../views/DonateView.vue';
import ContactFormView from '../views/ContactFormView.vue';
/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: MainView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/apply",
    name: "apply",
    component: ApplyView,
    meta: {
    requiresAuth: false
    }
  },
  {
    path: "/user",
    name: "user",
    component: UserView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/pet",
    name: "pet",
    component: PetView,
    meta: {
      requiresAuth: false
    }
  },
  // Changed applications meta to true for requiresAuth
  {
    path: "/applications",
    name: "applications",
    component: ApplicationsList,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/admin",
    name: "admin",
    component: AdminView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/addPet",
    name: "addPet",
    component: AddPetView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/forgotPassword",
    name: "forgotPassword",
    component: ForgotPasswordForm,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/adoptionForm",
    name: "adoptionForm",
    component: AdoptionFormView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/adoptionApps",
    name: "adoptionApps",
    component: AdoptionApplicationsList,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/search",
    name: "search",
    component: SearchView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/aboutUs",
    name: "aboutUs",
    component: AboutUsView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/donate",
    name: "donate",
    component: DonateView,
    meta: {
      requiresAuth: false
  }
  },
  {
    path: "/contact",
    name: "contact",
    component: ContactFormView,
    meta: {
      requiresAuth: false
    }
  },

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
