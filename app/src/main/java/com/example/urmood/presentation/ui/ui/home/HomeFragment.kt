package com.example.urmood.presentation.ui.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urmood.R
import com.example.urmood.databinding.FragmentHomeBinding
import com.example.urmood.presentation.ui.TestActivity
import com.example.urmood.presentation.ui.WebviewActivity
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

        homeViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        homeViewModel.listArticle.observe(viewLifecycleOwner){
            setArticle(it)
        }
        homeViewModel.listTips.observe(viewLifecycleOwner){
            setTips(it)
        }
        binding.btnTest.setOnClickListener {
            startActivity(Intent(requireActivity(), TestActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTips(tips : List<TipsResponse>){
        binding.tvMotivation.text = tips.first().tips
    }

    private fun setArticle(article : List<ArticleResponse>){
        val adapter = ListArticleAdapter(article)
        binding.rvArticle.adapter = adapter
        adapter.setOnItemClickCallback(object :
            ListArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ArticleResponse?) {
                val bundle = Bundle()
                bundle.putString("link", data?.link)
                startActivity(Intent(requireActivity(), WebviewActivity::class.java).putExtra(
                    "link", data?.link))
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbHome.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}