//package com.example.retrofitbair.presentetion.screens
//class SearchFragment : Fragment() {
//    private lateinit var binding: FragmentSearchBinding
//    private val apiService: ProductApi by inject()   // private val phoneViewModel by viewModel<PhoneViewModel>()
//    private val phoneViewModel: PhoneViewModel by activityViewModels() private lateinit var adapter: SearchAdapter
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentSearchBinding.inflate(
//            layoutInflater,
//            container,
//            false
//        )        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        adapter = SearchAdapter()
//        binding.rcVieww.layoutManager = LinearLayoutManager(requireContext())
//        binding.rcVieww.adapter = adapter
//
//        binding.serachView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                phoneViewModel.token.observe(viewLifecycleOwner) { token ->
//                    CoroutineScope(Dispatchers.IO).launch {
//                        delay(500)
//                        val request = newText?.let {
//                            apiService.getProductsByName(
//                                token,
//                                it
//                            )
//                        }
//                        requireActivity().runOnUiThread {
//                            adapter.submitList(request?.products)
//                        }
//                    }
//                }
//                return true
//            }
//        })
//    }
//}
