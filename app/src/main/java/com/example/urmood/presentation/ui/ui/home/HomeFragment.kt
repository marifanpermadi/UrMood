package com.example.urmood.presentation.ui.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urmood.databinding.FragmentHomeBinding
import com.example.urmood.presentation.ui.TestActivity
import com.example.urmood.presentation.ui.ui.ListArticleAdapter
import com.example.urmood.presentation.ui.ui.model.ArticleResponse
import com.example.urmood.presentation.ui.ui.model.TipsResponse

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnTest.setOnClickListener {
            val intent = Intent(requireContext(), TestActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticle.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(),layoutManager.orientation)
        binding.rvArticle.addItemDecoration(itemDecoration)
        homeViewModel.listTips.observe(viewLifecycleOwner){
            setTips(it)
        }
        homeViewModel.listArticle.observe(viewLifecycleOwner){
            setArticle(it)
        }
        homeViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        binding.btnTest.setOnClickListener {
            Toast.makeText(requireContext(), binding.tvMotivation.text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTips(tips : TipsResponse){
        binding.tvMotivation.text = tips.tipsResponse!!.first()!!.tips
    }

    private fun setArticle(article : ArticleResponse){
        val adapter = ListArticleAdapter(article.articleResponse)
        binding.rvArticle.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbHome.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}