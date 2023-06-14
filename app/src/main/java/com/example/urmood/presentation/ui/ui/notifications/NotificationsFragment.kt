package com.example.urmood.presentation.ui.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.urmood.R
import com.example.urmood.presentation.ui.ui.model.UserSession
import com.example.urmood.databinding.FragmentProfileBinding
import com.example.urmood.presentation.ui.AboutAppActivity
import com.example.urmood.presentation.ui.LoginActivity

class NotificationsFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getUserData()

        binding.tvAbout.setOnClickListener {
            val intent = Intent(requireActivity(), AboutAppActivity::class.java)
            startActivity(intent)
        }

        binding.tvLogout.setOnClickListener {
            logout()
        }
    }

    private fun getUserData() {
        val notificationsViewModel = ViewModelProvider(this)[NotificationsViewModel::class.java]
        val email = UserSession.loggedInUserEmail

        notificationsViewModel.getUserData(email)
        showLoading(true)

        notificationsViewModel.userData.observe(viewLifecycleOwner) { userData ->
            showLoading(false)
            binding.tvName.text = userData.fullname
            binding.tvEmail.text = userData.email
        }

        notificationsViewModel.error.observe(viewLifecycleOwner) {
            showLoading(false)
            Toast.makeText(requireContext(), getString(R.string.fail_fetching_user_data), Toast.LENGTH_LONG).show()
        }
    }

    private fun logout() {
        val notificationsViewModel = ViewModelProvider(this)[NotificationsViewModel::class.java]
        val email = UserSession.loggedInUserEmail

        notificationsViewModel.logout(email)
        showLoading(true)

        notificationsViewModel.logout.observe(viewLifecycleOwner) {
            showLoading(false)
            Toast.makeText(requireContext(), getString(R.string.logout_success), Toast.LENGTH_SHORT).show()

            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}